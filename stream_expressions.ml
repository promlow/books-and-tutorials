        Objective Caml version 3.12.1

# #load "dynlink.cma";;
# #load "camlp4o.cma";;
	Camlp4 Parsing version 3.12.1

# [< >];;
- : 'a Stream.t = <abstr>
# let more_numbers = [< '1; '2; '(1+2) >] (* Equivalent to Stream.of_list [1; 2;3;] *);;
val more_numbers : int Stream.t = <abstr>
# [< '1; '2; more_numbers; '99 >];;
- : int Stream.t = <abstr>
# let ones =
    let rec aux () =
      [< '1; aux () >] in
    aux();;
      val ones : int Stream.t = <abstr>
# let rec ones = 1 :: ones;;
val ones : int list =
  [1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1; 1;
   ...]
# let rec ones = [< '1; ones >];;
Characters 18-20:
  let rec ones = [< '1; ones >];;
                    ^^
Error: This kind of expression is not allowed as right-hand side of `let rec'
# let rec const_stream k = [< 'k; const_stream k >];;
val const_stream : 'a -> 'a Stream.t = <fun>
# let rec count_stream i = [< 'i; count_stream (i + 1) >];;
val count_stream : int -> int Stream.t = <fun>
# let fib =
    let rec aux a b =
      [< '(a + b); aux b (a + b) >] in
    [< '0; '1; aux 0 1 >];;
      val fib : int Stream.t = <abstr>
# Stream.npeek 10 fib;;
- : int list = [0; 1; 1; 2; 3; 5; 8; 13; 21; 34]
# let rec walk dir =
    let items =
      try
	Array.map
	  (fun fn ->
	    let path = Filename.concat dir in
	    try
	      if Sys.is_directory path
	      then `Dir path
	      else `File path
	    with e -> `Error (path, e))
	  (Sys.readdir dir)
      with e -> [| `Error (dir, e) |] in
    Array.fold_right
      (fun item rest ->
	match item with
	| `Dir path -> [< 'item; walk path; rest >]
	| _ -> [< 'item; rest >])
      items
      [< >];;
                                      Characters 145-149:
  	      if Sys.is_directory path
                             ^^^^
Error: This expression has type string -> string
       but an expression was expected of type string
# 

Process ocaml-toplevel finished
