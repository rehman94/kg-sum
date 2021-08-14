import React, { useRef, useEffect } from 'react';
import ForceGraph2D from "react-force-graph-2d";

function GraphContainer(props) {

    const forceRef = useRef(null);
    useEffect(() => {
      forceRef.current.d3Force("charge").strength(-10);
    });
    return (
      <ForceGraph2D
        graphData={props.graphData}
        nodeLabel="name"
        nodeAutoColorBy="group"
        linkDirectionalParticles="value"
        linkDirectionalParticleSpeed={(d) => d.value * 0.001}
            ref={forceRef}
      />
    );
  }

export default GraphContainer;