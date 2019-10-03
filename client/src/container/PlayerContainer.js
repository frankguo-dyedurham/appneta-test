import React from "react";
import Client from "../Client";
import Player from "../component/Player.js"

class PlayerContainer extends React.Component {
    constructor(){
        super();
        this.state = {
            players: [],
            filteredPlayers: [],
            countryCounts: {}
        };
    }

    updateKeyword(evt) {
      const { players } = this.state

      let fltPlys = this.filter(players, evt.target.value)
      this.setState({
        filteredPlayers: fltPlys
      })

      let natCounts = this.countNat(fltPlys)
      this.setState({
        countryCounts: natCounts
      })
    }

    countNat(plys){
      let cntyCounts = {}
      plys.forEach(ply => {
        let natCount = cntyCounts[ply.nationality]
        if (natCount != null) {
          cntyCounts[ply.nationality] = ++natCount
        } else {
          cntyCounts[ply.nationality] = 1
        }
      })

      return cntyCounts
    }

    filter(players, keyword) {
      if(players != null) {
        return players.filter(ply =>{
          return Object.values(ply).some(val => 
              val.toString().toLowerCase().indexOf(keyword.toLowerCase()) !== -1)
        })
      }
      return []
    }

    componentDidMount(){
        Client.allPlayers(plys=>{
          this.setState({
              players: plys,
              filteredPlayers: plys,
              countryCounts: this.countNat(plys)
          })
        });
    }

    render(){
        console.log("test")
        const { filteredPlayers, countryCounts } = this.state;
        return <Player filteredPlayers={filteredPlayers} countryCounts={countryCounts}
                    updateKeyword={this.updateKeyword.bind(this)}/>
    }
}

export default PlayerContainer;