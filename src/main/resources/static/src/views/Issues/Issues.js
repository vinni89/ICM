import React, { Component } from 'react';
import axios from 'axios';
import {Button, Card, CardBody, CardHeader, CardFooter, Col, Row, Table} from "reactstrap";
import swal from 'sweetalert';
import {Link} from "react-router-dom";

class Issues extends Component {
    constructor(props) {
        super(props);

        this.state = {
            issues: [],
            page: 0,
            size: 10
        };
    }

    componentWillMount() {
        var _this = this;
        axios.get("/api/issues?page=" + this.state.page + "&size=" + this.state.size)
            .then(function(response) {
                _this.setState({
                    issues: response.data.content
                });
            })
            .catch(function (error) {
                swal({title: "Something went wrong!", text: error, icon: "error"});
            });
    }

    onNavigate(navUri) {
        var _this = this;
    	axios.get(navUri)
            .then(function(response) {
    		_this.setState({
                issues: response.data.content,
    			attributes: this.state.attributes,
    			pageSize: this.state.pageSize,
    			links: response.data.content._links
    		});
    	});
    }

    handleNavFirst(e){
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.first.href);
    }

    handleNavPrev(e) {
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.prev.href);
    }

    handleNavNext(e) {
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.next.href);
    }

    handleNavLast(e) {
    	e.preventDefault();
    	this.props.onNavigate(this.props.links.last.href);
    }

    render() {
         	var navLinks = [];
        	if ("first" in this.props.links) {
        		navLinks.push(<Button color="primary" key="first" onClick={this.handleNavFirst}>First</Button>);
        	}
        	if ("prev" in this.props.links) {
        		navLinks.push(<Button color="primary" key="prev" onClick={this.handleNavPrev}>Previous</Button>);
        	}
        	if ("next" in this.props.links) {
        		navLinks.push(<Button color="primary" key="next" onClick={this.handleNavNext}>Next</Button>);
        	}
        	if ("last" in this.props.links) {
        		navLinks.push(<Button color="primary" key="last" onClick={this.handleNavLast}>Last</Button>);
        	}

        return (
            <div className="animated fadeIn">
                <Row>
                    <Col xs="12" lg="12">
                        <Card>
                            <CardHeader>Issue list</CardHeader>
                            <CardBody>
                                <Table responsive bordered>
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Title</th>
                                        <th>Initial Date</th>
                                        <th>Category</th>
                                        <th>User ID</th>
                                        <th>Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        {this.state.issues.map(function(issue, i) {
                                            return (<Issue key={i} issue={issue}/>);
                                        })}
                                    </tbody>
                                </Table>
                            </CardBody>
                            <CardFooter className="text-center">
                                {navLinks}
                            </CardFooter>
                        </Card>
                    </Col>
                </Row>
            </div>
        )
    }
}

class Issue extends Component {

    constructor(props) {
        super(props);

        this.state = {
            issue: this.props.issue,
            mounted: true
        };

        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete(){
        var _this = this;
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this data!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then(function(willDelete) {
                if (willDelete) {
                    axios.delete("/api/issues/"+_this.state.issue.id)
                        .then(function(response) {
                            _this.setState({
                                mounted: false
                            });
                            swal({title: "Issue record deleted", icon: "success"});
                        }).catch(function (error) {
                        swal({title: "Something went wrong!", text: error, icon: "error"});
                    });
                }
            });
    }

    render() {
        const row = (
            <tr>
                <td>{this.state.issue.id}</td>
                <td>{this.state.issue.title}</td>
                <td>{this.state.issue.initialDate}</td>
                <td>{this.state.issue.category}</td>
                <td>{this.state.issue.userDto.id}</td>
                <td>
                    <Link to={"/issues/"+this.state.issue.id+"/edit"}><Button color="info" size="sm">Edit</Button></Link>{' '}
                    <Button color="danger" size="sm" onClick={this.handleDelete}>Delete</Button>
                </td>
            </tr>
        );
        return (this.state.mounted === true ? row : null)
    }
}

export default Issues;