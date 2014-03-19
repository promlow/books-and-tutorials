#include "fail.h"
#include "grade.h"

using std::list;

list<Student_info> extract_fails(list<Student_info>& students)
{
    list<Student_info> fails;
    list<Student_info>::iterator iter = students.begin();

    while (iter != students.end()) {
	if (fgrade(*iter)) {
	    fails.push_back(*iter);
	    iter = students.erase(iter);
	} else
	    ++iter;
    }

    return fails;
}
