#include <algorithm>
#include <iostream>
#include <string>
#include <vector>
#include <climits>

using std::cin;      using std::cout;
using std::vector;   using std::endl;
using std::string;   using std::sort;

int main()
{
    vector<string> words;

    cout << "Enter a list of words: ";
    string s;
    while ( cin >> s){
	words.push_back(s);
    }
    string::size_type min = s.max_size();
    string::size_type max = 0;
    string::size_type curr = 0;

    for(int i = 0; i < words.size(); i++){
	curr = words[i].size();
	if(curr < min)
	    min = curr;
	if ( curr > max )
	    max = curr;
    }

    if(curr){
	cout << "Longest word was: " << max << endl;
	cout << "Shortest word was: " << min << endl;
    } else {
	cout << endl << "No words entered." << endl;
	return 1;
    }
    
    return 0;
}
