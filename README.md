# ice-dms-core
Core module for the ice-dms project. Defines all the system interfaces.

An extremely easy to integrate, standalone Document Management System for small businesses.
 
This is the first piece of the whole application, namely the core module.
Key functionalities are:
* Different storage types.
* Document indexing.
* User defined indexing rules on documents per document type.
* Document groups to help define composite indexing rules.
* Document tagging.
* Notes on documents.
* Versioned and lockable content to ensure acid operations on all the data and metadata.
* Integration layer to offer different kinds of external access to the edms system.
* Extensible internal authentication and authurozation mechanism.

Nice to have:
* User defined workflow based on document type and/or document group.

Key properties are:
* Straitforward and low cost integration in legacy applications using REST and SOAP WebServices.
* Portability.
* Flexibility.
* Low cost maintainance.

This is the core module that contains all the interfaces to this application which is divided 4 in different packages:
* Business layer
* DAO Layer
* Service Layer (service orchestration layer)
** Command objects that wrap service requests.
** Command objects are mapped to specific requests.
** Low level services can be used independetly and with a great flexibility.
* Persentation layer (Spring WEB MVC)

LICENSE (DWTFYUWALAYDMMWI V1.0)
Please read the terms and conditions specified in the LICENSE file.
The license applies to this and all submodules of this piece of software.
