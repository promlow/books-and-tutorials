#include <iostream>

using std::cout;     using std::cin;
using std::endl;

int main()
{
    int x, y;
    cout << "Please enter 2 numbers, separated by a space: ";
    cin >> x;
    cin >> y;

    if ( x > y ){
	cout << x << " is greater than " << y << endl;
    } else if (x < y ) {
	cout << x << " is less than " << y << endl;
    } else {
	cout << x << " and " << y << " are equal." << endl;
    }
    return 0;
}
