const {describe, expect, test} = require("@jest/globals");

// supertest is a framework that allows to easily test web APIs
const supertest = require('supertest');
const app = require('./../app');
const request = supertest(app);

describe('REST APIs for registered_students', () =>
{
    describe('GET /registered_students', () =>
    {
        test('should return a 200 (ok) status code', async() =>
        {
            // version 1
            const response = await request.get('/registered_students');
            expect(response.status).toBe(200);

            // or

            // version 2
            await request.get('/registered_students').expect(200);
        });

        test('should have Content-Type "application/json"', async() =>
        {
            const response = await request.get('/registered_students');
            expect(response.header['content-type']).toMatch(/application\/json/);

            // or

            await request.get('/registered_students').expect('Content-Type', /application\/json/);
        });

        test('should contain the key "studentId" in the first class returned as a JSON response', async() =>
        {
            const response = await request.get('/registered_students');
            const response_content_as_json = response.body;

            expect(response_content_as_json[0]).toHaveProperty('studentId');
        });

        test('should contain "1" in the first studentId returned as a JSON response', async() =>
        {
            const response = await request.get('/registered_students');
            const response_content_as_json = response.body;

            expect(response_content_as_json[0].studentId).toBe(1);
        });
    });

    describe('POST /add_student_to_class', () =>
    {
        test('should return a 400 response when the "classId" field is missing in the request', async() =>
        {
            // this is the form data that we will send in our POST request to the server
            const form_data = {
                studentId: '1',
                // classId: '2'
            };

            const response = await request
                .post('/add_student_to_class')
                // .set('Content-Type', 'application/x-www-form-urlencoded') // this is equivalent to .type('form')
                .type('form')
                .send(form_data);     // send form data as the request body

            expect(response.status).toBe(400);
        });

        test('should return a 201 response when the request has all required fields and all field values are valid', async() =>
        {
            const form_data = {
                studentId: '1',
                classId: '2'
            };

            const response = await request
                .post('/add_student_to_class')
                // .set('Content-Type', 'application/x-www-form-urlencoded') // this is equivalent to .type('form')
                .type('form')
                .send(form_data);     // send form data as the request body

            expect(response.status).toBe(201);
        });
    });

    describe('DELETE /drop_student_from_class', () =>
    {
        test('should return a 422 response when the "studentId" field is missing in the request', async() =>
        {
            // this is the form data that we will send in our POST request to the server
            const form_data = {
                // studentId: '1',
                classId: '2'
            };

            const response = await request
                .delete('/drop_student_from_class')
                // .set('Content-Type', 'application/x-www-form-urlencoded') // this is equivalent to .type('form')
                .type('form')
                .send(form_data);     // send form data as the request body

            expect(response.status).toBe(422);
        });

        test('should return a 422 response when deleting the class with id = 999 which does not exist', async() =>
        {
            const form_data = {
                studentId: '1',
                classId: '999'
            };

            const response = await request
                .delete('/drop_student_from_class')
                // .set('Content-Type', 'application/x-www-form-urlencoded') // this is equivalent to .type('form')
                .type('form')
                .send(form_data);     // send form data as the request body

            expect(response.status).toBe(422);
        });

        test('should return a 204 response when deleting the class with id = 1 and student id = 8', async() =>
        {
            // this is the form data that we will send in our POST request to the server
            const form_data = {
                studentId: '1',
                classId: '8'
            };

            const response = await request
                .delete('/drop_student_from_class')
                // .set('Content-Type', 'application/x-www-form-urlencoded') // this is equivalent to .type('form')
                .type('form')
                .send(form_data);     // send form data as the request body

            expect(response.status).toBe(204);
        });
    });

    describe('GET /students_taking_class/:classCode', () =>
    {
        // TODO: add your tests
    });

    describe('GET /registered_students_taken_by_student/:studentId', () =>
    {
        // TODO: add your tests
    });
});
