#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

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

    sort(words.begin(), words.end());
    
    int start = 0;
    int count = 0;

    while (start < words.size()){
	string word = words[start];
	count = 1;
	while ( start + count < words.size() && 
		words[start + count] == word ){
	    count++;
	}
	start += count;
	cout << word << " occurs " << count << " times" << endl;
    }
    return 0;
}
