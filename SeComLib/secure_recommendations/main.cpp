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
@file secure_recommendations/main.cpp
@brief Secure Recommendations main entry point.
@details Simulation for "Privacy-Preserving Recommender Systems in eHealth Systems", Arjan Jeckmans, Pieter Hartel, Michael Beye, Zekeriya Erkin, Mihai Todor, Inald Lagendijk, Jeroen Doumen, Tanya Ignatenko, 2013.
@author Mihai Todor (todormihai@gmail.com)
*/

#include "main.h"

/**
Application entry point.

Usage: Accepts one optional parameter: the full path to the configuration file. Otherwise, it tries to find "config.xml" in the current directory.

@param argc number of command line arguments
@param argv array containing the command line arguments
@return The process exit status
*/
int main (int argc, char *argv[]) {
	try {
		//the first command line parameter always contains the name of the application
		if (argc > 1) {
			//a custom configuration file was specified
			Utils::Config::SetConfigFile(std::string(argv[1]));
		}

		/// Initialize the hub and the server
		std::shared_ptr<Hub> hub = std::make_shared<Hub>();
		std::shared_ptr<Server> server = std::make_shared<Server>(hub->GetPublicKey());

		/// Make the hub and the server aware of each other
		hub->SetServer(server);
		server->SetClient(hub);

		/// Initialize the server!!!
		server->Initialize();

		//set the default analysis type to accuracy
		std::string analysisType = Utils::Config::GetInstance().GetParameter<std::string>("SecureRecommendations.analysisType", "accuracy");

		/// Start the analysis
		if (analysisType == "accuracy") {
			hub->DoAccuracyAnalysis();
		}
		else if (analysisType == "performance") {
			hub->DoPerformanceAnalysis();
		}
		else if (analysisType == "traffic") {
			hub->DoTrafficAnalysis();
		}
		else {
			throw std::runtime_error("Not implemented yet.");
		}
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