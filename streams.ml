        Objective Caml version 3.12.1

# let line_stream_of_channel channel =
  Stream.from
    (fun _ =>
	try Some (input_line channel) with End_of_file -> None);;
      Characters 62-64:
      (fun _ =>
             ^^
Error: Syntax error
# let line_stream_of_channel channel =
    Stream.from
      (fun _ ->
	try Some (input_line channel) with End_of_file -> None);;
      val line_stream_of_channel : in_channel -> string Stream.t = <fun>
# let in_channel = open_in "_oasis";;
Exception: Sys_error "_oasis: No such file or directory".
# let in_channel = open_in "_oasis";;
val in_channel : in_channel = <abstr>
# let lines = line_stream_of_channel in_channel;;
val lines : string Stream.t = <abstr>
# Stream.next lines;;
- : string = "OASISFormat: 0.3"
# Streain.next lines;;
Characters 0-12:
  Streain.next lines;;
  ^^^^^^^^^^^^
Error: Unbound module Streain
# Stream.next lines;;
- : string = "Version: 0.3.2"
# Stream.next lines;;
- : string = "Synopsis: BDD for OCaml"
# while true do ignore(Stream.next lines) done;;
Exception: Stream.Failure.
# #load "str.cma";;
# let line_stream_of_string string =
    Stream.of_list (Str.split (Str.regexp "\n") string);;
  val line_stream_of_string : string -> string Stream.t = <fun>
# let lines = line_stream_of_string "hello\nstream\nworld";;
val lines : string Stream.t = <abstr>
# Stream.next lines;;
- : string = "hello"
# Stream.next lines;;
- : string = "stream"
# Stream.next lines;;
- : string = "world"
# Stream.next lines;;
Exception: Stream.Failure.
# let in_channel = open_in "_oasis" in
  try
    Stream.iter
      (fun line ->
	(* do something with line *)
	print_endline line)
      (line_stream_of_channel in_channel);
    close_in in_channel
  with e ->
    close_in in_channel;
    raise e;;
                    OASISFormat: 0.3
Name: ospec
Version: 0.3.2
Synopsis: BDD for OCaml
Authors: Andre Nathan
LicenseFile: LICENSE
License: MIT
InstallType: Custom (0.2)
XCustomInstall: EXEC_PREFIX=$exec_prefix make install
XCustomUninstall: make uninstall


Library ospec
  Path: src
  BuildTools: ocamlbuild
  CompiledObject: byte
  Modules: Helpers, Prop, Spec, Gen, Pa_spec, Report
Executable ospec
  Path: src
  BuildTools: ocamlbuild
  MainIs: ospec.ml
  CompiledObject: byte
  BuildDepends: ospec
- : unit = ()
# let empty_stream () = Stream.from (fun _ -> None);;
val empty_stream : unit -> 'a Stream.t = <fun>
# let const_stream k = Stream.from (fun _ -> Some k);;
val const_stream : 'a -> 'a Stream.t = <fun>
# let count_stream i = Stream.from (fun j -> Some (i + j));
;
;;
    Characters 58-59:
  ;
  ^
Error: Syntax error
# let count_stream i = Stream.from (fun j -> Some (i + j));;
val count_stream : int -> int Stream.t = <fun>
# let lines = line_stream_of_string "hello\nworld";;
val lines : string Stream.t = <abstr>
# Stream.peek lines;;
- : string option = Some "hello"
# Stream.peek lines;;
- : string option = Some "hello"
# Sream.junk lines;;
Characters 0-10:
  Sream.junk lines;;
  ^^^^^^^^^^
Error: Unbound module Sream
# Stream.junk lines;;
- : unit = ()
# Stream.peek lines;;
- : string option = Some "world"
# Stream.junk lines;;
- : unit = ()
# Stream.peek lines;;
- : string option = None
# let paragraphs lines =
    let rec next para_lines i =
      match Stream.peek lines, para_lines with
      | None, [] -> None
      | Some "", [] ->
	Stream.junk lines;
	next para_lines i
      | Some "", _ | None, _ ->
	Some (String.concat "\n" (List.rev para_lines))
      | Some line, _ ->
	Stream.junk lines;
	next (line :: para_lines) i in
    Stream.from (next []);;
                        val paragraphs : string Stream.t -> string Stream.t = <fun>
# let lines = line_stream_of_channel in_channel in
  Stream.iter
    (fun para ->
      print_endline para;
      print_endline "--")
    (paragraphs lines);;
          - : unit = ()

''
;;
    Characters 1-2:
  ''
  ^
Error: Syntax error
# let stream_map f stream =
    let rec next i =
      try Some (f (Stream.next stream))
      with Stream.Failure -> None in
    Stream.from next

  let stream_filter p stream =
    let rec next i =
      try
	let value = Stream.next stream in
	if p value then Some value else next i
      with Stream.Failure -> None in
    Stream.from next

  let stream_fold f stream init =
    let result = ref init in
    Stream.iter
      (fun x -> result := f x !result)
      stream;
    !result;;
                                      val stream_map : ('a -> 'b) -> 'a Stream.t -> 'b Stream.t = <fun>
val stream_filter : ('a -> bool) -> 'a Stream.t -> 'a Stream.t = <fun>
val stream_fold : ('a -> 'b -> 'b) -> 'a Stream.t -> 'b -> 'b = <fun>
# let is_leap year =
    year mod 4 = 0 && (year mod 100 <> 0 || year mod 400 = 0);;
  val is_leap : int -> bool = <fun>
#let is_leap year =
    year mod 4 = 0 && (year mod 100 <> 0 || year mod 400 = 0)
 let leap_years = stream_filter is_leap (count_stream 2000);;
    val is_leap : int -> bool = <fun>
val leap_years : int Stream.t = <abstr>
# Stream.npeek 30 leap_years;;
- : int list =
[2000; 2004; 2008; 2012; 2016; 2020; 2024; 2028; 2032; 2036; 2040; 2044;
 2048; 2052; 2056; 2060; 2064; 2068; 2072; 2076; 2080; 2084; 2088; 2092;
 2096; 2104; 2108; 2112; 2116; 2120]
# stream_fold (+) (Stream.of_list [1; 2; 3]) 0;;
- : int = 6
# let cycle items =
    let buf = ref [] in
    let rec next i =
      if !buf = [] then buf := items;
      match !buf with
      | h :: t -> (buf := t; some h)
      | [] -> None in
    Stream.from next;;
              Characters 152-156:
        | h :: t -> (buf := t; some h)
                               ^^^^
Error: Unbound value some
# let cycle items =
    let buf = ref [] in
    let rec next i =
      if !buf = [] then buf := items;
      match !buf with
      | h :: t -> (buf := t; Some h)
      | [] -> None in
    Stream.from next;;
              val cycle : 'a list -> 'a Stream.t = <fun>
# let stream_combine stream1, stream2 =
    let rec next i = 
      try Some (Stream.next stream1, Stream.next stream2)
      with Stream.Failure -> None in
    Stream.from next;;
        Characters 26-27:
  let stream_combine stream1, stream2 =
                            ^
Error: Syntax error
# let stream_combine stream1 stream2 =
    let rec next i = 
      try Some (Stream.next stream1, Stream.next stream2)
      with Stream.Failure -> None in
    Stream.from next;;
        val stream_combine : 'a Stream.t -> 'b Stream.t -> ('a * 'b) Stream.t = <fun>
# Stream.iter print_endline
	  (stream_map
	     (fun (bg, s) ->
	       Printf.sprintf "<div style='background %s'>%s</div>" bg s)
	     (stream_combine
		(cycle ["#eee"; "#fff"])
		(Stream.of_list ["hello"; "html"; "world"])));;
            <div style='background #eee'>hello</div>
<div style='background #fff'>html</div>
<div style='background #eee'>world</div>
- : unit = ()
# let range ?(start=0) ?(stop=0) ?(step=1) () =
    let in_range = if step < 0 then (>) else (<) in
    let current = ref start in
    let rec next i =
      if in_range !current stop
      then let result = !current in (current := !current + step;
				     Some result)
      else None in
    Stream.from next;;
                val range : ?start:int -> ?stop:int -> ?step:int -> unit -> int Stream.t =
  <fun>
# Stream.npeek 10 (range ~start:5 ~stop:10 ());;
- : int list = [5; 6; 7; 8; 9]
# Stream.npeek 10 (range ~stop:10 ~step:2 ());;
- : int list = [0; 2; 4; 6; 8]
# Stream.npeek 10 (range ~start:10 ~step:(-1) ());;
- : int list = [10; 9; 8; 7; 6; 5; 4; 3; 2; 1]
# Stream.npeek 10 (range ~start:10 ~stop:5 ~step:(-1) ());;
- : int list = [10; 9; 8; 7; 6]
# let stream_concat streams =
    let current_stream = ref None in
    let rec next i =
      try
        let stream =
          match !current_stream with
          | Some stream -> stream
          | None ->
             let stream = Stream.next streams in
             current_stream := Some stream;
             stream in
        try Some (Stream.next stream)
        with Stream.Failure -> (current_stream := None; next i)
      with Stream.Failure -> None in
    Stream.from next;;
                            val stream_concat : 'a Stream.t Stream.t -> 'a Stream.t = <fun>
# Stream.npeek 10
    (stream_concat
       (stream_map
          (fun i -> range ~stop:i ())
          (range ~stop:5 ())));;
        - : int list = [0; 0; 1; 0; 1; 2; 0; 1; 2; 3]
# let stream_combine stream1 stream2 =
    let rec next i =
      try Some (Stream.next stream1, Stream.next stream2)
      with Stream.Failure -> None in
    Stream.from next;;
        val stream_combine : 'a Stream.t -> 'b Stream.t -> ('a * 'b) Stream.t = <fun>
# let stream_tee stream =
    let next self other i =
      try
	if Queue.is_empty self
	then
	  let value = Stream.next stream in
	  Queue.add value other;
	  Some value
	else
	  Some (Queue.take self)
      with Stream.Failure -> None in
    let q1 = Queue.create () in
    let q2 = Queue.create () in 
    (Stream.from (next q1 q2), Stream.from (next q2 q1));;
                          val stream_tee : 'a Stream.t -> 'a Stream.t * 'a Stream.t = <fun>
# let letters = Stream.of_list [ 'a'; 'b'; 'c'; 'd'; 'e'; 'f'];;
val letters : char Stream.t = <abstr>
# let s1, s2 = stream_tee letters;;
val s1 : char Stream.t = <abstr>
val s2 : char Stream.t = <abstr>
# Stream.next s1;;
- : char = 'a'
# Stream.next s1;;
- : char = 'b'
# Stream.next s2;;
- : char = 'a'
# Stream.next s1;;
- : char = 'c'
# Stream.next s2;;
- : char = 'b'
# Stream.next s2;;
Characters 12-14:
  Stream.next s3;;
              ^^
Error: Unbound value s3
# Stream.next s2;;
- : char = 'c'
# Stream.next letters;;
- : char = 'd'
# Stream.next s1;;
- : char = 'e'
# Stream.next s2;;
- : char = 'e'
# let stream_of_list = Stream.of_list

  let list_of_stream stream =
    let result = ref [] in
    Stream.iter (fun value -> result := value :: !result) stream;
    List.rev !result

  let stream_of_array array =
    Strea.of_list (Array.to_list array)

  let array_of_stream stream =
    Array.of_list (list_of_stream stream)

  let stream_of_hash =
    let result = ref [] in
    Hashtbl.iter
      (fun key value -> result := (key, value) :: !result)
      hash;
    Stream.of_list !result

  let hash_of_stream stream =
    let result = Hashtbl.create 0 in
    Stream.iter
      (fun (key, value) -> Hashtbl.replace result key value)
      stream;
    result;;
                                                  Characters 216-229:
      Strea.of_list (Array.to_list array)
      ^^^^^^^^^^^^^
Error: Unbound module Strea
# let stream_of_list = Stream.of_list

  let list_of_stream stream =
    let result = ref [] in
    Stream.iter (fun value -> result := value :: !result) stream;
    List.rev !result

  let stream_of_array array =
    Stream.of_list (Array.to_list array)

  let array_of_stream stream =
    Array.of_list (list_of_stream stream)

  let stream_of_hash =
    let result = ref [] in
    Hashtbl.iter
      (fun key value -> result := (key, value) :: !result)
      hash;
    Stream.of_list !result

  let hash_of_stream stream =
    let result = Hashtbl.create 0 in
    Stream.iter
      (fun (key, value) -> Hashtbl.replace result key value)
      stream;
    result;;
                                                  Characters 460-464:
        hash;
        ^^^^
Error: Unbound value hash
# let stream_of_list = Stream.of_list

  let list_of_stream stream =
    let result = ref [] in
    Stream.iter (fun value -> result := value :: !result) stream;
    List.rev !result

  let stream_of_array array =
    Stream.of_list (Array.to_list array)

  let array_of_stream stream =
    Array.of_list (list_of_stream stream)

  let stream_of_hash hash =
    let result = ref [] in
    Hashtbl.iter
      (fun key value -> result := (key, value) :: !result)
      hash;
    Stream.of_list !result

  let hash_of_stream stream =
    let result = Hashtbl.create 0 in
    Stream.iter
      (fun (key, value) -> Hashtbl.replace result key value)
      stream;
    result;;
                                                  val stream_of_list : 'a list -> 'a Stream.t = <fun>
val list_of_stream : 'a Stream.t -> 'a list = <fun>
val stream_of_array : 'a array -> 'a Stream.t = <fun>
val array_of_stream : 'a Stream.t -> 'a array = <fun>
val stream_of_hash : ('a, 'b) Hashtbl.t -> ('a * 'b) Stream.t = <fun>
val hash_of_stream : ('a * 'b) Stream.t -> ('a, 'b) Hashtbl.t = <fun>
# #directory "+threads";;
# #load "threads.cma";;
Characters -1--1:
  #load "threads.cma";;
  
Error: Reference to undefined global `Unix'
# 