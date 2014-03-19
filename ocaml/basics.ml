        Objective Caml version 3.12.1

# let rec range a b = 
  if a > b then []
  else a :: range (a + 1) b;;
    val range : int -> int -> int list = <fun>
# (* rec defines a recursive function. *)
;;
  Characters 40-42:
  ;;
  ^^
Error: Syntax error
# let positive_sum a b =
  let a = max a 0
  and b = max b 0 in
  a + b;;
      val positive_sum : int -> int -> int = <fun>
# range 1 10
;;
  - : int list = [1; 2; 3; 4; 5; 6; 7; 8; 9; 10]
# positive_sum 1 10;;
- : int = 11
# positive_sum -1 -10;;
Characters 0-12:
  positive_sum -1 -10;;
  ^^^^^^^^^^^^
Error: This expression has type int -> int -> int
       but an expression was expected of type int
# let give_me_a_three x = 3;;
val give_me_a_three : 'a -> int = <fun>
# give_me_a_three [];;
- : int = 3
# let average a b = 
    (a +. b) /. 2.0;;
  val average : float -> float -> float = <fun>
# 

Process ocaml-toplevel finished
