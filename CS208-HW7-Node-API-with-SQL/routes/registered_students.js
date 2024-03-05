let express = require('express');
let router = express.Router();
const db = require("./../db");


/**
 * GET /registered_students
 *
 * @return a list of registered students (extracted from a join between
 * registered_students, students and classes tables in the database) as JSON
 */
router.get("/registered_students", async function (req, res)
{
    try
    {
        const listOfRegisteredStudentJoinResults = await db.getAllRegisteredStudents();
        console.log({listOfRegisteredStudentJoinResults});

        res.send(listOfRegisteredStudentJoinResults);
    }
    catch (err)
    {
        console.error("Error:", err.message);
        res.status(500).json({"error": "Internal Server Error"});
    }
});


/**
 * POST /add_student_to_class
 * with the following form parameters:
 *      studentId
 *      classId
 *
 * The parameters passed in the body of the POST request will be inserted
 * into the registered_students table in the database.
 */
router.post("/add_student_to_class", async function (req, res)
{
    try
    {
        const studentId = req.body.studentId;
        const classId = req.body.classId;

        console.log("studentId        = " + studentId);
        console.log("classId       = " + classId);

        if (studentId === undefined)
        {
            res.status(400).json({"error": "bad request: expected parameter 'studentId' is not defined"});
            return;
        }

        if (classId === undefined)
        {
            res.status(400).json({"error": "bad request: expected parameter 'classId' is not defined"});
            return;
        }

        // let registerStudent = {
        //     id: null, // will be initialized by the database, after we insert the record
        //     studentId: studentId,
        //     classId: classId
        // };

        registerStudent = await db.addStudentToClass(studentId, classId);

        // return 201 status code (i.e., created)
        res.status(201).json(registerStudent);
    }
    catch (err)
    {
        console.error("Error:", err.message);
        res.status(422).json({"error": "failed to enroll student to class"});
    }
});


/**
 * DELETE /drop_student_from_class
 * with the following form parameters:
 *      studentId
 *      classId
 *
 * Deletes the student with id = {studentId} from the class with id = {classId}
 * from the registered_students in the database.
 *
 * @throws a 404 status code if the student with id = {studentId} does not exist
 * @throws a 404 status code if the class with id = {classId} does not exist
 */
router.delete("/drop_student_from_class", async function (req, res)
{
    try
    {
        const studentId = req.body.studentId;
        const classId = req.body.classId;

        console.log("studentId        = " + studentId);
        console.log("classId       = " + classId);

        const classToDelete = await db.getClassWithId(classId);
        console.log({classToDelete});

        if (classToDelete == null)
        {
            console.log("No class with id " + classId + " exists.");

            // return 404 status code (i.e., error that the class was not found)
            res.status(404).json({"error": "failed to drop the class with id = " + id + " from the database because it does not exist"});
            return;
        }

        const studentToDelete = await db.getStudentWithId(studentId);
        console.log({studentToDelete});

        if (studentToDelete == null)
        {
            console.log("No student with id " + studentId + " exists.");

            // return 404 status code (i.e., error that the student was not found)
            res.status(404).json({"error": "failed to drop the student with id = " + id + " from the database because it does not exist"});
            return;
        }
        await db.dropAnExistingStudentFromAClass(studentId, classId);

        res.status(204).send(); //there is no return value
    }
    catch (err)
    {
        console.error("Error:", err.message);
        res.status(422).json({"error": "failed to drop student from class"});
    }
});


/**
 * GET /students_taking_class/{classCode}
 *
 * @return a list of registered students (extracted from a join between
 * registered_students, students and classes tables in the database) as JSON
 * that are taking the class {classCode}
 */
// TODO: implement this route


/**
 * GET /classes_in_which_student_is_enrolled/{studentId}
 *
 * @return a list of all classes (extracted from a join between
 * registered_students, students and classes tables in the database) as JSON
 * in which the student with id = {studentId} is enrolled
 *
 * @throws a 404 status code if the student with id = {studentId} does not exist
 */
// TODO: implement this route


module.exports = router;
