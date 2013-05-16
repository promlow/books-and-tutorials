#include <algorithm>
#include <iostream>
#include "Student_info.h"

using std::max;
using std::cout; 
using std::endl;
using std::string;

int main()
{
    Student_info s;
    string::size_type maxlen;

    cout << max(s.name.size(), maxlen) << endl;
    return 0;
}
