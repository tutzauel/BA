/*
SeComLib
Copyright 2012-2013 TU Delft, Information Security & Privacy Lab (http://isplab.tudelft.nl/)

Contributors:
Inald Lagendijk (R.L.Lagendijk@TUDelft.nl)
Mihai Todor (todormihai@gmail.com)
Thijs Veugen (P.J.M.Veugen@tudelft.nl)
Zekeriya Erkin (z.erkin@tudelft.nl)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
/**
@file secure_face_recognition/main.cpp
@brief Secure Face Recognition main entry point.
@details Simulation for "Privacy-Preserving Face Recognition", Zekeriya Erkin, Martin Franz, Jorge Guajardo, Stefan Katzenbeisser, Inald Lagendijk, Tomas Toft, 2009 (with speedup enhancements as described in Martin Franz' Master Thesis from 2008)
This implementation contains only the secure comparison protocol.
@author Mihai Todor (todormihai@gmail.com)
*/

#include "main.h"
#include "core/big_integer.h"

/**
Application entry point.

Usage: Accepts one optional parameter: the full path to the configuration file. Otherwise, it tries to find "config.xml" in the current directory.

@param argc number of command line arguments
@param argv array containing the command line arguments
@return The process exit status
*/
int main (int argc, char *argv[]) {
	try {
		//Pfad zu der XML File 
		Utils::Config::SetConfigFile(std::string("/home/tutzauer/Desktop/Ba/Cpp/config.xml"));	
		
		//3 Argumnte erwartet
		if (argc != 4) {
		std::cout << "ERROR" << "\n";
}
	//argv[1] = fingercode 
	//argv[2] = fingercodeDB
	//argv[3] = threshold

	BigInteger a(std::string(argv[1]), 10);
	BigInteger b(std::string(argv[2]), 10);
	BigInteger t(std::string(argv[3]), 10);

		/// Initialize the servers
		std::shared_ptr<Client> client = std::make_shared<Client>();
		std::shared_ptr<Server> server = std::make_shared<Server>(client->paillierCryptoProvider.GetPublicKey(), client->dgkCryptoProvider.GetPublicKey());

		server->SetClient(client);
		client->SetServer(server);

		/// Start the simulation
		std::cout << "main.cpp: Starte Simulation mit(newFCode, fcodeFromDBAtI,threshold)" << std::endl;
		client->StartSimulation(a, b, t);
	}
	catch (const std::runtime_error &exception) {
		std::cout << exception.what() << std::endl;
	}
	catch (const std::exception &exception) {
		std::cout << exception.what() << std::endl;
	}
	//it won't catch low level exceptions, like division by 0, produced by GMP...
	catch (...) {
		std::cout << "Unexpected exception occured." << std::endl;
	}
	return 0;
}
