const {describe, expect, test} = require("@jest/globals");

// supertest is a framework that allows to easily test web APIs
const supertest = require('supertest');
const app = require('./../app');
const request = supertest(app);

describe('REST APIs for students', () =>
{
    describe('GET /students', () =>
    {
        test('should return a 200 (ok) status code', async() =>
        {
            // version 1
            const response = await request.get('/students');
            expect(response.status).toBe(200);

            // or

            // version 2
            await request.get('/students').expect(200);
        });

        test('should have Content-Type "application/json"', async() =>
        {
            const response = await request.get('/students');
            expect(response.header['content-type']).toMatch(/application\/json/);

            // or

            await request.get('/students').expect('Content-Type', /application\/json/);
        });

        test('should contain the key "firstName" in the first student returned as a JSON response', async() =>
        {
            const response = await request.get('/students');
            const response_content_as_json = response.body;

            expect(response_content_as_json[0]).toHaveProperty('firstName');
        });

        test('should contain "Alice" in the first student firstName returned as a JSON response', async() =>
        {
            const response = await request.get('/students');
            const response_content_as_json = response.body;

            expect(response_content_as_json[0].firstName).toBe('Alice');
        });
    });

    describe('GET /students/:id', () =>
    {
        test('should return a 200 status code', async() =>
        {
            const response = await request.get('/students/1');
            expect(response.status).toBe(200);
        });

        test('should have Content-Type "application/json"', async() =>
        {
            const response = await request.get('/students/1');
            expect(response.header['content-type']).toMatch(/application\/json/);
        });

        test('should contain the correct student as JSON', async() =>
        {
            const response = await request.get('/students/1');
            const actual_response_content_as_json = response.body;

            const expected_response_as_json = {
                id: 1,
                firstName: 'Alice',
                lastName: 'Agnesi',
                birthDate: '1991-01-01',
            };

            expect(actual_response_content_as_json).toEqual(expected_response_as_json);
        });

        test('should return a 404 (not found) status code when the class with id = 999 does not exist', async() =>
        {
            const response = await request.get('/students/999');
            expect(response.status).toBe(404);
        });
    });

    describe('POST /students', () =>
    {
        test('should return a 400 response when the "lastName" field is missing in the request', async() =>
        {
            // this is the form data that we will send in our POST request to the server
            const form_data = {
                firstName: 'Chris',
                // lastName: 'Nichols',
                birthDate: '2001-02-03'
            };

            const response = await request
                .post('/students')
                // .set('Content-Type', 'application/x-www-form-urlencoded') // this is equivalent to .type('form')
                .type('form')
                .send(form_data);     // send form data as the request body

            expect(response.status).toBe(400);
        });

        test('should return a 201 response when the request has all required fields and all field values are valid', async() =>
        {
            const form_data = {
                firstName: 'Chris',
                lastName: 'Nichols',
                birthDate: '2001-02-03'
            };

            const response = await request
                .post('/students')
                .type('form')
                .send(form_data);

            expect(response.status).toBe(201);
        });

        test('should return a 422 response when the length of the "firstName" field is > 30 in the request', async() =>
        {
            const form_data = {
                firstName: 'Chris67890123456789012345678901',
                lastName: 'Nichols',
                birthDate: '2001-02-03'
            };

            const response = await request
                .post('/students')
                .type('form')
                .send(form_data);

            expect(response.status).toBe(422);
        });

        test('should return a 422 response when the length of the "lastName" field is > 50 in the request', async() =>
        {
            const form_data = {
                firstName: 'Chris',
                lastName: 'Nichols89012345678901234567890123456789012345678901',
                birthDate: '2001-02-03'
            };

            const response = await request
                .post('/students')
                .type('form')
                .send(form_data);

            expect(response.status).toBe(422);
        });

    });

    describe('PUT /students/:id', () =>
    {
        // TODO: add your tests
    });

    describe('PATCH /students/:id', () =>
    {
        // TODO: add your tests
    });

    describe('DELETE /students/:id', () =>
    {
        // TODO: add your tests
    });
});
