#ifndef GUARD_fail_container_h
#define GUARD_fail_container_h
// fail.h
#include "Student_info.h"

#ifdef LIST

#include <list>
typedef std::list<Student_info> stu_container;

#else

#include <vector>
typedef std::vector<Student_info> stu_container;

#endif


stu_container extract_fails(stu_container&);

#endif
