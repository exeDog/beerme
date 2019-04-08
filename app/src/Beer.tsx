import * as React from 'react';


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
        return<div>
            <h2>Beer List</h2>
            {beers.map((beer: Beer) =>
                <div key={beer.id} style={{display:"flex",justifyContent:"center"}}>
                    <div style={{padding:"5px"}}>{beer.name}</div>
                    <div style={{padding:"5px"}}>{beer.price}</div>
                    <div style={{padding:"5px"}}>{beer.alchoholContent}</div>
                </div>
            )}

            <div>
                <button onClick={()=>this.getByFilter('Content')}>Click Here for Strong beers</button>
            </div>
        </div>
    }
}


export default BeerList;