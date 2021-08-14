import logo from './logo.svg';
import React, { Component } from 'react';
import './App.css';
import GraphContainer from './components/GraphContainer/GraphContainer';
import output from './output.json';

class App extends Component  {


    state = {
        dataParsed: false,
        nodeIds: [],
        nodes: [],
        links: [],
        typeProps: {
        Album: ["artist", "producer", "track", "name", "date", "description"],
        Song: ["writer", "name", "description"],
        Songwriter: ["member", "name", "description"],
        SoloArtist: ["name", "description"],
        Producer: ["name", "description"],
        Band: ["member", "name", "description"],
        },
    };

    shouldComponentUpdate(nextProps, nextState) {
        return nextState.dataParsed !== this.state.dataParsed;
    }

    componentDidMount() {
        output["@graph"].forEach((element, index) => {
        if (index > 150) {
            this.setState({
            dataParsed: true,
            });
        } else {
            this.handleNode(element, Object.keys(element)[1], index);
        }
        });
    }

    arrayAdd(array, element) {
        const newArray = array.slice();
        newArray.push(element);
        return newArray;
    }


    handleObject(elemId, object, objectType, group) {
        this.setState((prevState) => {
        if (!prevState.nodeIds.includes(object)) {
            const uriParts = object.split("/");
            return {
            nodeIds: this.arrayAdd(prevState.nodeIds, object),
            nodes: this.arrayAdd(prevState.nodes, {
                id: object,
                name: decodeURIComponent(uriParts[uriParts.length - 1]),
                group: group,
            }),
            links: this.arrayAdd(prevState.links, {
                source: elemId,
                target: object,
                name: objectType,
                // value: group,
            }),
            };
        } else {
            return {
            links: this.arrayAdd(prevState.links, {
                source: elemId,
                target: object,
                name: objectType,
                // value: group,
            }),
            };
        }
        });
    }

    handleNode(element, elemType, group) {
        this.setState((prevState) => {
        if (!prevState.nodeIds.includes(element["@id"])) {
            let uriParts = element["@id"].split("/");

            let typeParts = elemType.split("/");
            const type = typeParts[typeParts.length - 1];

            const objElem = element[elemType];
            let objTypeParts = elemType.split("/");

            this.handleObject(element["@id"], objElem['@type'] + '_' + objElem['@value'], decodeURIComponent(objTypeParts[objTypeParts.length - 1]), group);

            // this.state.typeProps[type].forEach((prop) => {
            // if (prop in element) {
            //     if (typeof element[prop] === "string") {
            //     this.handleObject(element["@id"], element[prop], prop, group);
            //     } else if (typeof element[prop] === "object") {
            //     element[prop].forEach((subProp) => {
            //         this.handleObject(element["@id"], subProp, prop, group);
            //     });
            //     }
            // }
            // });
            return {
                nodeIds: this.arrayAdd(prevState.nodeIds, element["@id"]),
                nodes: this.arrayAdd(prevState.nodes, {
                    id: element["@id"],
                    name: decodeURIComponent(uriParts[uriParts.length - 1]),
                    group: group,
                }),
            };
        }
        });
    }

  render() {
      
    let graphData = {
        nodes: this.state.nodes,
        links: this.state.links,
      };
    return (
      <div className="App">
        <GraphContainer
            graphData = {graphData} />
      </div>
    );
  }
}

export default App;
