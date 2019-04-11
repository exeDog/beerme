import * as React from 'react';
import './App.css';

enum Content {
    LIGHT,
    MILD,
    STRONG,
    VERY_STRONG,
    POISON,
}

interface AppState {
    type: string,
    content: Content
}

interface AppProps {}

class SideSort extends React.Component<AppProps,AppState> {

    constructor(props: AppProps) {
        super(props);
        this.state = {
            type: '',
            content: Content.LIGHT,
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event: React.FormEvent<HTMLSelectElement>): void{
        const value =  event.currentTarget.value;
        this.setState({type: value});
    }

    render(): React.ReactNode {

        const {type} = this.state;
        return <>
            <div className="sideBar">
                <span>
                    <label>Sort By</label>
                <select value={type} onChange={event1 => this.handleChange(event1)}>
                    <option value='price'>Price</option>
                    <option value='content'>Content</option>
                    <option value='name'>Name</option>
                </select>
                </span>
            </div>
        </>;
    }


}


export default SideSort;