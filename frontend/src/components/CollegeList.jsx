import React, { useEffect, useState } from 'react';
import CollegeRow from './CollegeRowData';
import '../App.css';

const CollegeList = () => {
    const [colleges, setColleges] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchColleges = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/colleges/all'); 
                if (!response.ok) {
                    throw new Error('Data nhi hai, shi kro');
                }
                const data = await response.json();
                console.log(data);
                setColleges(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchColleges();
    }, []);

    if (loading) {
        return <div>Loading colleges...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="table-container">
            <h1>Colleges and Courses</h1>
            {colleges.length === 0 ? (
                <p>No colleges available</p>
            ) : (
                <div className="table-scroll">
                    <table>
                        <thead>
                            <tr>
                                <th>College Name</th>
                                <th>Course Name</th>
                                <th>Course Fee</th>
                                <th>Duration of Course</th>
                                <th>Accommodation Type</th>
                                <th>Accommodation Fee</th>
                            </tr>
                        </thead>
                        <tbody>
                            {colleges.map(college => (
                                college.courses && college.courses.length > 0 ? (
                                    college.courses.map(course => (
                                        <CollegeRow
                                            key={course.id}
                                            college={college}
                                            course={course}
                                            fee={course.courseFee}
                                        />
                                    ))
                                ) : (
                                    <tr key={college.id}>
                                        <td colSpan="6">No courses available</td>
                                    </tr>
                                )
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default CollegeList;
