(*
 * Parser
 *)

(* binop_precedence - This holds the precedence for each binary operator
 * that is defined.
 *)
let binop_precedence: (char, int) Hashtbl.t = Hashtbl.create 10

(* precedence - Get the precedence of the pending binary operator token *)
let precedence c = try Hashtbl.find binop_precedence c with Not_found -> -1

(* primary
 *  ::= identifier
 *  ::= numberexpr
 *  ::= parenexpr
 *)
let rec parse_primary = parser
  (* number expr ::= number *)
  | [< 'Token.Number n >] -> Ast.Number n

  (* parenexpr ::= '(' expression ')' *)
  | [< 'Token.Kwd '('; e=parse_expr; 'Token.Kwd ')' ?? "expected ')'" >] -> e

  (* identifier
   *  ::= identifier
   *  ::= identifier '(' argumentexpr ')' *)
   *)
  | [< 'Token.Ident id; stream >] ->
    let rec parse_args accumulator = parser
    | [< e=parse_expr; stream >] ->
      begin parser
	  | [< 'Token.Kwd ','; e=parse_args (e :: accumulator) >] -> e
	  | [< >] -> e :: accumulator
      end stream
    | [< >] -> accumulator
    in
    let rec parse_ident id = parser
      (* Call. *)
      | [< 'Token.Kwd '(';
	   args=parse_args [];
	   'Token.Kwd ')' ?? "expected ')'" >] ->
	Ast.Call (id, Array.of_list (List.rev args))

      (* Simple variable ref. *)
      | [< >] -> Ast.Variable id
    in
    parse_ident id stream

  | [< >] -> raise (Stream.Error "unknow token when expecting an expression.")

(* binoprhs
 * ::= ('+' primary)* *)
and parse_bin_rhs expr_prec lhs stream =
  match Stream.peek stream with
  (* if this is a binop, find its precedence *)
  | Some (Token.Kwd c) when Hashtbl.mem binop_precedence c ->
    let token_prec = precedence c in
   
    (* if this is a binop that binds at least as tightly as the current
       binop, consume it, otherwise we are done *)
    if token_prec < expr_prec then lhs else begin
      (* consume the binop *)
      Stream.junk stream;

      (* parse the primary expression after the binary op *)
      let rhs = parse_primary stream in
      
      let rhs =
	match Stream.peek stream with
	| Some (Token.Kwd c2) ->
	  (* if BinOp binds less tightly with rhs than the operator after
	   * rhs, let the pending operator take rhs as its lhs *)
	  let next_prec = precedence c2 in
	  if token_prec < next_prec
	  then parse_bin_rhs (token_prec + 1) rhs stream
	  else rhs
	| _ -> rhs
      in

      (* merge lhs/rhs *)
      let lhs = Ast.Binary (c, lhs, rhs) in
      parse_bin_rhs expr_expr lhs stream
    end
  | _ -> lhs

(* expression
 * ::= primary binoprhs *)

and parse_expr = parser
  | [< lhs=parse_primary; stream >] -> parse_bin_rhs 0 lhs stream

(* prototype
 *   ::= id '(' id* ')' *)
let parse_prototype =
  let rec parse_args accululator = parser
  | [< 'Token.Ident id; e=parse_args (id::accumulator) >] -> e
  | [< >] -> accumulator
  in

  parser
  | [< 'Token.Ident id;
       'Token.Kwd '(' ?? "expected '(' in prototype";
       args=parse_args [];
       'Token.Kwd ')' ?? "expected ')' in prototype" >] ->
    (* success *)
    Ast.prototype (id, Array.of_list (List.rev args))

  | [< >] ->
    raise (Stream.error "expected function name in prototype")

(* definition ::= 'def' prototype expression *)
let parse_definition = parser
  | [< 'Token.Def; p=parse_prototype; e=parse_expr >] ->
    Ast.Function (p, e)

(* toplevelexpr ::= expr *)
let toplevel_expr = parser
  | [< e=parse_expr >] ->
    (* make an anonymous proto *)
    Ast.Function (Ast.Prototype ("", [||]), e)
		    
(* external ::= 'extern' prototype *)
let parse_extern = parser
  | [< 'Token.Extern; e=parse_prototype >] -> e

