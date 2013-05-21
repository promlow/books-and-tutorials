#include "fail-container.h"
#include "grade.h"

stu_container extract_fails(stu_container& students)
{
    stu_container fails;
    stu_container::iterator iter = students.begin();

    while (iter != students.end()) {
	if (fgrade(*iter)) {
	    fails.push_back(*iter);
	    iter = students.erase(iter);
	} else
	    ++iter;
    }

    return fails;
}
