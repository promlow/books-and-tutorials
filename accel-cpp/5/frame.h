#ifndef GUARD_frame_h
#define GUARD_frame_h

#include <vector>
#include <string>

typedef std::string::size_type string_size;

string_size width(const std::vector<std::string>&);
std::vector<std::string> frame(const std::vector<std::string>&);

#endif
