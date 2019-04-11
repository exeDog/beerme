import * as React from 'react';
import SideBar from './SideSort';


interface Beer{
    id: number;
    name: string;
    price: number;
    alchoholContent: string;
}

interface AppProps {}

interface AppState {
    beers: Array<Beer>;
    isLoading: boolean;
}


class BeerList extends React.Component<AppProps, AppState>{

    constructor(props: AppProps) {
        super(props);

        this.state = {
            beers: [],
            isLoading: false,
        };

        this.getByFilter = this.getByFilter.bind(this);
    }

    async componentDidMount() {
        this.setState({isLoading: true});

        await fetch("/api/all-beers")
            .then(response => response.json())
            .then( data => this.setState({beers: data, isLoading: false}));
    }

    async getByFilter(value: string){
        if(value === "Content"){
            this.setState({isLoading: true});
            await fetch(`/api/beers/STRONG`)
                .then(response => response.json())
                .then(data => this.setState({beers: data, isLoading: false}))
        }
    }

    render(): React.ReactNode {
        const {beers} = this.state;

        if(this.state.isLoading){
            return <div>...Loading</div>
        }
        return<>
            <h2>Beer List</h2>
            <div style={{display:'flex', justifyContent: 'center'}}>
                <SideBar/>
                <div style={{paddingLeft:'50px'}}>
                    {beers.map((beer: Beer) =>
                        <div key={beer.id} style={{display:"flex",justifyContent:"center"}}>
                            <div style={{padding:"5px"}}>{beer.name}</div>
                            <div style={{padding:"5px"}}>{beer.price}</div>
                            <div style={{padding:"5px"}}>{beer.alchoholContent}</div>
                        </div>
                    )}
                </div>
            </div>
        </>
    }
}


export default BeerList;