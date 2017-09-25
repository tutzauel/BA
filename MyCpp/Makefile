#SeComLib
#Copyright 2012-2013 TU Delft, Information Security & Privacy Lab (http://isplab.tudelft.nl/)
#
#Contributors:
#Inald Lagendijk (R.L.Lagendijk@TUDelft.nl)
#Mihai Todor (todormihai@gmail.com)
#Thijs Veugen (P.J.M.Veugen@tudelft.nl)
#Zekeriya Erkin (z.erkin@tudelft.nl)
#
#Licensed under the Apache License, Version 2.0 (the "License");
#you may not use this file except in compliance with the License.
#You may obtain a copy of the License at
#
#http://www.apache.org/licenses/LICENSE-2.0
#
#Unless required by applicable law or agreed to in writing, software
#distributed under the License is distributed on an "AS IS" BASIS,
#WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#See the License for the specific language governing permissions and
#limitations under the License.

#@file Makefile
#@brief Sample Linux Makefile
#@author Mihai Todor (todormihai@gmail.com)

CXX = g++

SECOMLIB_DIR = /home/tutzauer/SeComLib

#Set this to LIB_MPIR, if using the MPIR library
BIG_NUMBER_LIB_NAME = LIB_GMP
BIG_NUMBER_LIB_DIR = /usr/local

BOOST_INCLUDE_DIR = /usr/include/boost
BOOST_LIB_DIR = /usr/lib/x86_64-linux-gnu
BOOST_LINK_LIBRARIES = -lboost_timer -lboost_chrono -lboost_system -lboost_filesystem

CXXFLAGS = -Wall -Wextra -Wconversion -Wno-unknown-pragmas -pedantic -std=c++11 -O2 -I . -I $(BIG_NUMBER_LIB_DIR)/include -I $(BOOST_INCLUDE_DIR) -I $(SECOMLIB_DIR) -D $(BIG_NUMBER_LIB_NAME) -D NDEBUG

LDFLAGS = -static -lCore -lUtils $(BOOST_LINK_LIBRARIES) -lgmp -lrt -L $(BIG_NUMBER_LIB_DIR)/lib -L $(SECOMLIB_DIR)/_output/linux -L $(BOOST_LIB_DIR)
#main: *.cpp, *h
main: *.cpp
	rm -rf ./main
	$(CXX) *.cpp $(CXXFLAGS) $(LDFLAGS) -o $@
