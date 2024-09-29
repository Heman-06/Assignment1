const CollegeRow = ({ college, course, fee }) => {
    return (
        <tr>
            <td>{college.name}</td>
            <td>{course.name}</td>
            <td>{fee.fee}</td>
            <td>{course.duration}</td>
            <td>{fee.accommodationType}</td>
            <td>{fee.accommodationFee}</td>
        </tr>
    );
};

export default CollegeRow;
