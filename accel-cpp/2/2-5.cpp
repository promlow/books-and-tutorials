#include <iostream>

using std::cout;
using std::endl;
using std::string;

int main()
{
    for(int i = 0; i < 5; i++) {
	for(int j = 0; j <= i; j++) {
	    cout << "*";
	}
	cout << endl;
    }
    
    cout << endl << endl;

    const string sq(5, '*');
    for(int i = 0; i < 5; i++) {
	cout << sq << endl;
    }

    cout << endl << endl;

    const string rect(10, '*');
    for(int i = 0; i < 5; i++) {
	cout << rect << endl;
    }
    
}
