#include <algorithm>
#include <iostream>
#include <vector>

using std::sort;
using std::cin;      using std::cout;
using std::vector;   using std::endl;

int main()
{
    vector<int> nums;
    int tmp;
    
    cout << "Enter at least 4 integers: ";
    
    while ( cin >> tmp ){
	nums.push_back( tmp );
    }

    if ( nums.size() < 4 ) {
	cout << "Enter at least 4 integers!";
	return 1;
    }

    sort(nums.begin(), nums.end());
    
    int quarter = nums.size() / 4;
    for (int i = 0; i < 4; i++ ){
	int idx = quarter * (3 - i);
	cout << "Quartile #" << i + 1 << ":" << endl;
	for ( int j = 0; j < quarter; j++ ) {
	    cout << nums[idx + j] << endl;
	}
    }
    return 0;
}
 
