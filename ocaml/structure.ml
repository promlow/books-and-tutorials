        Objective Caml version 3.12.1

# let average a b =
  let sum = a +. b in
  sum /. 2.0;;
    val average : float -> float -> float = <fun>
# let f a b =
  (a +. b) +. (a +. b) ** 2.
  ;;
    val f : float -> float -> float = <fun>
# let f' a b =
    let x = a +. b in
    x +. x ** 2.
  ;;
      val f' : float -> float -> float = <fun>
# f 3 5;;
Characters 2-3:
  f 3 5;;
    ^
Error: This expression has type int but an expression was expected of type
         float
# f 3. 5.;;
- : float = 72.
# f' 3. 5.;;
- : float = 72.
# ref 0;
;
;;
    Characters 7-8:
  ;
  ^
Error: Syntax error
# ref 0;;
- : int ref = {contents = 0}
# let my_ref = ref 0;;
val my_ref : int ref = {contents = 0}
# my_ref := 100;;
- : unit = ()
# !my_ref;;
- : int = 100
# let my_ref = ref 0;; (* int a = 0; int *my_ptr = &a; *)
val my_ref : int ref = {contents = 0}
# my_ref := 100;       (* *my_ptr = 100; *)
;;
  - : unit = ()
# !my_ref;;            (* *my_ptr *)
- : int = 100
# (* C/C++ comparison of references/pointers *)
;;
  Characters 46-48:
  ;;
  ^^
Error: Syntax error
# let read_whole_channel chan = 
    let buf = Buffer.create 4096 in
    let rec loop () =
      let newline = input_line chan in
      Buffer.add_string buf newline;
      Buffer.add_char buf '\n';
      loop()
    in
    try
      loop()
    with
      End_of_file -> Buffer.contents buf;;
                      val read_whole_channel : in_channel -> string = <fun>
# open Graphics;;
# 
open_graph " 640x480";;
  Characters -1--1:
  
  
Error: Reference to undefined global `Graphics'
# open Graphics;;
# open_graph " 640x480";;
Characters -1--1:
  open_graph " 640x480";;
  
Error: Reference to undefined global `Graphics'
# Random.set_init ();;
Characters 0-15:
  Random.set_init ();;
  ^^^^^^^^^^^^^^^
Error: Unbound value Random.set_init
# let x = ;
;;
  Characters 8-9:
  let x = ;
          ^
Error: Syntax error
# ;
;;
  Characters 0-1:
  ;
  ^
Error: Syntax error
# a ; b ; c ; d ;;
Characters 0-1:
  a ; b ; c ; d ;;
  ^
Error: Unbound value a
# let a = 0;;
val a : int = 0
# let b = 1;;
val b : int = 1
# let c = 2;;
val c : int = 2
# let d = 3;;
val d : int = 3
# a; b; c; d; ;;
Characters 0-1:
  a; b; c; d; ;;
  ^
Warning 10: this expression should have type unit.
Characters 3-4:
  a; b; c; d; ;;
     ^
Warning 10: this expression should have type unit.
Characters 6-7:
  a; b; c; d; ;;
        ^
Warning 10: this expression should have type unit.
- : int = 3
# a + b + c + d;;
- : int = 6
# (* I don't really understand the 'Note about ";" section' *) a;;
- : int = 0
# 

Process ocaml-toplevel finished
