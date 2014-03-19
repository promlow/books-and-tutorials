        Objective Caml version 3.12.1

# [1; 2; 3];;
- : int list = [1; 2; 3]
# [];;
- : 'a list = []
# [1; 2; 3];;
- : int list = [1; 2; 3]
# 1 :: [2; 3];;
- : int list = [1; 2; 3]
# 1 :: 2 :: [3];;
- : int list = [1; 2; 3]
# 1 :: 2 :: 3 :: [];;
- : int list = [1; 2; 3]
# List.length;;
- : 'a list -> int = <fun>
# (3, 4)
;;
  - : int * int = (3, 4)
# (3, "hello", 'x');;
- : int * string * char = (3, "hello", 'x')
# type pair_of_ints = { a : int; b : int };;
type pair_of_ints = { a : int; b : int; }
# { a = 3; b = 5 };;
- : pair_of_ints = {a = 3; b = 5}
# type foo = Nothing | Int of int | Pair of int * int | String of string;;
type foo = Nothing | Int of int | Pair of int * int | String of string
# Nothing;;
- : foo = Nothing
# Int 3;;
- : foo = Int 3
# Pair (4, 5);;
- : foo = Pair (4, 5)
# String "hello";;
- : foo = String "hello"
# type sign = Positive | Zero | Negative;;
type sign = Positive | Zero | Negative
# type binary_tree = Leaf of int | Tree of binary_tree * binary_tree;;
type binary_tree = Leaf of int | Tree of binary_tree * binary_tree
# Leaf 3;
;;
  - : binary_tree = Leaf 3
# Tree (Leaf 3, Leaf 4);;
- : binary_tree = Tree (Leaf 3, Leaf 4)
# Tree (Tree (Leaf 3, Leaf 4), Leaf 5)
;;
  - : binary_tree = Tree (Tree (Leaf 3, Leaf 4), Leaf 5)
# Tree(Tree (Leaf 3, Leaf 4), Tree (Tree (Leaf 3, Leaf 4), Leaf 5));;
- : binary_tree =
Tree (Tree (Leaf 3, Leaf 4), Tree (Tree (Leaf 3, Leaf 4), Leaf 5))
# type 'a binary_tree = Leaf of 'a | Tree of 'a binary_tree * 'a binary_tree;;
type 'a binary_tree = Leaf of 'a | Tree of 'a binary_tree * 'a binary_tree
# Leaf "Hello";;
- : string binary_tree = Leaf "Hello"
# Leaf 3.;;
- : float binary_tree = Leaf 3.
# Leaf 3.0;;
- : float binary_tree = Leaf 3.
# type 'a equiv_list = Nil | Cons of 'a * 'a equiv_list;;
type 'a equiv_list = Nil | Cons of 'a * 'a equiv_list
# Nil;;
- : 'a equiv_list = Nil
# Cons(1, Nil);;
- : int equiv_list = Cons (1, Nil)
# Cons(1, Cons(2, Nil));;
- : int equiv_list = Cons (1, Cons (2, Nil))
# type expr = Plus of expr * expr     (* means a + b *)
              | Minus of expr * expr  (* means a - b *)
              | Times of expr * expr  (* means a * b *)
              | Divide of expr * expr (* means a / b *)
	      | Value of string       (* "x", "y", "n", etc. *);;
        type expr =
    Plus of expr * expr
  | Minus of expr * expr
  | Times of expr * expr
  | Divide of expr * expr
  | Value of string
# Times (Value "n", Plus (Value "x", Value "y"));;
- : expr = Times (Value "n", Plus (Value "x", Value "y"))
# let rec to_string e =
    match e with
    | Plus (left, right)   -> "(" ^ to_string left ^ " + " ^ to_string right ^ ")"
    | Minus (left, right)  -> "(" ^ to_string left ^ " - " ^ to_string right ^ ")"
    | Times (left, right)  -> "(" ^ to_string left ^ " * " ^ to_string right ^ ")"
    | Divide (left, right) -> "(" ^ to_string left ^ " / " ^ to_string right ^ ")"
    | Value v -> v;;
            val to_string : expr -> string = <fun>
# let print_expr e = print_endline (to_string e);;
val print_expr : expr -> unit = <fun>
# print_expr (Times (Value "n", Plus (Value "x", Value "y")));;
(n * (x + y))
- : unit = ()
# let rec multiply_out e =
    match e with
    | Times (e1, Plus (e2, e3)) ->
        Plus (Times (multiply_out e1, multiply_out e2),
	      Times (multiply_out e1, multiply_out e3))
    | Times (Plus (e1, e2), e3) ->
        Plus (Times (multiply_out e1, multiply_out e3),
	      Times (multiply_out e2, multiply_out e3))
    | Plus (left, right) -> Plus (multiply_out left, multiply_out right)
    | Minus (left, right) -> Minus (multiply_out left, multiply_out right)
    | Times (left, right) -> Times (multiply_out left, multiply_out right)
    | Divide (left, right) -> Divide (multiply_out left, multiply_out right)
    | Value v -> Value v;;
                        val multiply_out : expr -> expr = <fun>
# print_expr (multiply_out (Times (Value "n", Plus (Value "x", Value "y"))));;
((n * x) + (n * y))
- : unit = ()
# let factorize e = 
    match e with
    | Plus (Times (e1, e2), Times (e3, e4)) when e1 = e3 ->
      Times (e1, Plus (e2, e4))
    | Plus (Times (e1, e2), Times (e3, e4)) when e2 = e4 ->
      Times (Plus (e1, e3), e4)
    | e -> e;;
            val factorize : expr -> expr = <fun>
# factorize (Plus (Times (Value "n", Value "x"), Times (Value "n", Value "y")));;
- : expr = Times (Value "n", Plus (Value "x", Value "y"))
# 

Process ocaml-toplevel finished
