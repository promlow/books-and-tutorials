//ask for a person's name, and greet the person
#include <iostream>
#include <string>

int main()
{
    //ask for the person's name
    std::cout << "Please enter your first name: ";
    
    // define and read the name
    std::string name; 
    std::cin >> name;

    //write a greeting
    std::cout << "Hello, " << name << "!" << std::endl;
}
