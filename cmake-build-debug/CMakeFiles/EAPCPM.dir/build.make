# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /cygdrive/c/Users/peter/.CLion2019.3/system/cygwin_cmake/bin/cmake.exe

# The command to remove a file.
RM = /cygdrive/c/Users/peter/.CLion2019.3/system/cygwin_cmake/bin/cmake.exe -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/EAPCPM.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/EAPCPM.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/EAPCPM.dir/flags.make

CMakeFiles/EAPCPM.dir/main.cpp.o: CMakeFiles/EAPCPM.dir/flags.make
CMakeFiles/EAPCPM.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/EAPCPM.dir/main.cpp.o"
	/usr/bin/c++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/EAPCPM.dir/main.cpp.o -c "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/main.cpp"

CMakeFiles/EAPCPM.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/EAPCPM.dir/main.cpp.i"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/main.cpp" > CMakeFiles/EAPCPM.dir/main.cpp.i

CMakeFiles/EAPCPM.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/EAPCPM.dir/main.cpp.s"
	/usr/bin/c++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/main.cpp" -o CMakeFiles/EAPCPM.dir/main.cpp.s

# Object files for target EAPCPM
EAPCPM_OBJECTS = \
"CMakeFiles/EAPCPM.dir/main.cpp.o"

# External object files for target EAPCPM
EAPCPM_EXTERNAL_OBJECTS =

EAPCPM.exe: CMakeFiles/EAPCPM.dir/main.cpp.o
EAPCPM.exe: CMakeFiles/EAPCPM.dir/build.make
EAPCPM.exe: CMakeFiles/EAPCPM.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/cmake-build-debug/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable EAPCPM.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/EAPCPM.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/EAPCPM.dir/build: EAPCPM.exe

.PHONY : CMakeFiles/EAPCPM.dir/build

CMakeFiles/EAPCPM.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/EAPCPM.dir/cmake_clean.cmake
.PHONY : CMakeFiles/EAPCPM.dir/clean

CMakeFiles/EAPCPM.dir/depend:
	cd "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/cmake-build-debug" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM" "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM" "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/cmake-build-debug" "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/cmake-build-debug" "/cygdrive/f/Uni Ano 3 Semestre 2/EA/Trabalhos/EAPCPM/cmake-build-debug/CMakeFiles/EAPCPM.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/EAPCPM.dir/depend

