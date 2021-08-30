# Question Answering system using GLIMPSE framework

## Requirements

- Python 3.4 or above (For Knowldge Graph Summarization)
- numpy
- scipy
- pandas
- JAVA 11 or above (For TeBaQA)
- ElasticSearch 6.6.1 (For TeBaQA)

## Data

Summarization is done over [DBPedia](https://www.dbpedia.org/) Knowldge Graph. Data sets we used are
- Mapping Based Objects
- Mapping Based Literals
- Labels
- Specific Mapping Based Properties
- Person Data


## Running the Project

### Summarizing Knowldge Graph
- Clone or download the project in desired directory
- In lines 16-18 of ``base.py``, change the path of DBPedia dataset according to your local data directories.
- In addition to this, you may need to change according to your directory structure and file naming conventions:
    - The subdirectory where queries are saved for the user(i.e. ``user0``, ``user1``, and ``user2`` for QALD8, QALD9, and LcQuAD respectively), The queries should be in WebQSP Format. If the queries are not in WebQSP format, you may change to ``script`` folder in order to convert the queries in desired format.
- To generate the summary, run this command in the base directory <br>
    ``python3 main.py``
- Incase you want to change code behaviour, you can edit arguments of ``parse_args`` method in ``main.py`` class. 
- Summaries are generated in the directory ``out0``,``out1``,and ``out2`` of the Project, according to user.

### Running Question Answering System
- These generated summaries along with DBPedia DataSets are then used by QA system(i.e. TeBaQA)
- clone or download the [TeBaQA](https://github.com/dice-group/TeBaQA). 
- DataSets are indexed on the ElasticSearch via TeBaQA class ``GenerateIndexes``.
- update the ``entityLinking.properties`` file in the base folder, according to directory where datasets are stored.
- After updating the properties file, run the following command in the base directory <br>
 ``mvn exec:java -Dexec.mainClass="de.uni.leipzig.tebaqa.indexGeneration.GenerateIndexes"``
- Once indexes are generated, Configure each microservice to run the Question Aswering System.
- Finally, we build the project and then run via scripts named ``build-script.sh`` and ``run-script.sh`` in the base folder.
- Project is started on the following url <br>
[http://localhost:8080](http://localhost:8080) 
## Citations

1. [Safavi,  Tara  et  al.  “Personalized  Knowledge  Graph  Summarization:   From  the  Cloud  to  YourPocket.” 2019 IEEE International Conference on Data Mining (ICDM) (2019):  528-537.](https://github.com/GemsLab/GLIMPSE-personalized-KGsummarization)
2. [Vollmers,  Daniel  et  al.  “Knowledge  Graph  Question  Answering  using  Graph-Pattern  Isomor-phism.” ArXiv abs/2103.06752 (2021):  n. pag](https://github.com/dice-group/TeBaQA)
