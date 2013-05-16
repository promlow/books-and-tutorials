#include <iostream>
#include <iomanip>
#include <string>

using std::cin;
using std::cout;
using std::endl;
using std::setw;

int main()
{    
    int max = 1000;
    int mm = max - 1;
    int slen = std::to_string(mm).size();
    int sqlen = std::to_string(mm * mm).size();

    for(int i = 1; i < max; i++)
	cout << setw(slen) << i << ' ' << setw(sqlen) << i * i << endl;

    return 0;
}
