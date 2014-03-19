#include <algorithm>
#include <iomanip>
#include <ios>
#include <iostream>
#include <stdexcept>
#include <string>
#include "grade.h"
#include "Student_info.h"
#include "fail-container.h"


using std::cin;                  using std::setprecision;
using std::cout;                 using std::sort;
using std::domain_error;         using std::streamsize;
using std::endl;                 using std::string;
using std::max;              

int main()
{
    stu_container students;
    Student_info record;
    string::size_type maxlen = 0;  //length of the longest name

    // read and store all the students data
    // Invariant: students contains all the student records read so far
    //   maxlen contains the length of the longest name in students
    while (read(cin, record)) {
	// find length of longest name
	maxlen = max(maxlen, record.name.size());
	students.push_back(record);
    }

#ifdef LIST
    // alphabetize the student records
    students.sort(compare);
#else
    sort(students.begin(), students.end(), compare);
#endif
    
    // write the names and grades
    for(stu_container::iterator i = students.begin();
	i != students.end(); ++i) {

	//write the name, padded on the right to maxlen + 1 characters
	cout << (*i).name
	     << string(maxlen + 1 - (*i).name.size(), ' ');

	//compute and write the grade
	try {
	    double final_grade = grade((*i));
	    streamsize prec = cout.precision();
	    
	    cout << setprecision(3) << final_grade
		 << setprecision(prec);
	} catch (domain_error e) {
	    cout << e.what();
	}
	cout << endl;
    }

    stu_container fails = extract_fails(students);

    return 0;
}
