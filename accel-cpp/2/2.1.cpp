#include <iostream>
#include <string>

int main()
{
    // ask for the person's name
    std::cout << "Please enter your first name: ";
    
    // read the name
    std::string name;
    std::cin >> name;

    //build the message to write
    const std::string greeting = "Hello, " + name + "!";

    // the number of blanks surrounding the greeting
    const int pad = 1;

    // total number of rows to write
    const int rows = pad * 2 + 3;

    // separate the output from the input
    std::cout << std::endl;

    // write rows number of rows output
    int r = 0;

    // invariant: we have written r rows so far
    while(r != rows){
	// write a row of output
	const std::string::size_type cols = greeting.size() + pad * 2 + 2;
	std::string::size_type c = 0;

	while(c != cols){
	    if (r==0 || r == rows -1 || c == 0 || c == cols - 1) {
		std::cout << "*";
		c++;
	    } else {
		if (r == pad + 1 && c == pad + 1) {
		    std::cout << greeting;
		    c += greeting.size();
		} else {
		    std::cout << " ";
		    c++;
		}
	    }
	}
	std::cout << std::endl;
	++r;
    }
    
    return 0;
}
