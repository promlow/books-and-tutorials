#include "frame.h"

using std::vector;
using std::string;
using std::max;

string_size width(const vector<string>& v)
{
    string_size maxlen = 0;
    for (vector<string>::size_type i = 0; i != v.size(); ++i)
	maxlen = max(maxlen, v[i].size());
    return maxlen;
}

vector<string> frame(const vector<string>& v)
{
    vector<string> ret;
    string_size maxlen = width(v);
    string border(maxlen + 4, '*');

    // write the top border
    ret.push_back(border);

    // write each interior row, bordered by an asterisk and a space
    for (vector<string>::size_type i = 0; i != v.size(); ++i) {
	ret.push_back( "* " + v[i] +
		       string(maxlen - v[i].size(), ' ') + " *");
    }

    // write the bottom border
    ret.push_back(border);
    return ret;
}
