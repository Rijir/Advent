#include <iostream>
#include <fstream>

int main(int argc, char** argv)
{
  if (argc != 2) {
    std::cout << "Usage: day1 input" << std::endl;
    return 1;
  }

  bool enteredBasement = false;
  int position = 1;
  int floor = 0;
  std::ifstream is(argv[1]);

  char c;
  while (is.get(c)) {
    if (c == '(')
      floor++;
    else if (c == ')')
      floor--;

    if (!enteredBasement && floor < 0) {
      std::cout << "Entered basement at: " << position << std::endl;
      enteredBasement = true;
    }
    position++;
  }

  is.close();
  std::cout << "Final floor: " << floor << std::endl;
  return 0;
}
