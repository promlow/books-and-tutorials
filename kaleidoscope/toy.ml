(*
 * main driver code
 *)

let main () = 
  (* install standard binary operators
   * 1 is the lowest precedence *)
  Hashtbl.add Parser.binop_precedence '<' 10;
  Hashtbl.add Parser.binop_precedence '+' 20;
  Hashtbl.add Parser.binop_precedence '-' 20;
  Hashtbl.add Parser.binop_precedence '*' 40; (* highest *)

  (* prime the first token *)
  print_string "ready >"; flush stdout;
  let stream = Lexer.lex (Stream.of_channel stdin) in

  (* run the main "interpreter loop" now *)
  Toplevel.main_loop stream;
;;

main()
