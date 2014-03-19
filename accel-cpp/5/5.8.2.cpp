#include <iostream>
#include <string>
#include <vector>
#include "frame.h"
#include "cat.h"

using std::string;
using std::vector;

using std::cin; using std::cout; using std::endl;

int main()
{
    vector<string> p;
    p.push_back("this is an");
    p.push_back("example");
    p.push_back("to");
    p.push_back("illustrate");
    p.push_back("framing");

    vector<string> r = frame(p);
    vector<string>::iterator i = r.begin();
    while (i != r.end()) {
	cout << (*i) << endl;
	++i;
    }
    cout << endl << endl;

    vector<string> rv = vcat(p, r);
    i = rv.begin();
    while (i != rv.end()) {
	cout << (*i) << endl;
	++i;
    }    
    cout << endl << endl;

    vector<string> rh = hcat(p, r);
    i = rh.begin();
    while (i != rh.end()) {
	cout << (*i) << endl;
	++i;
    }
    cout << endl << endl;

    return 0;
}
    
