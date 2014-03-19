#include <iostream>
#include <string>

int main()
{
    /* Entering first and last name must fill the input buffer
     * which is then read during the second cin >>
     */
    std::cout << "What is your name? ";
    std::string name;
    std::cin >> name;
    std::cout << "Hello, " << name
	      << std::endl << "And what is yours? ";
    std::cin >> name;
    std::cout << "Hello, " << name
	      << "; nice to meet you too!" << std::endl;
    return 0;
}
