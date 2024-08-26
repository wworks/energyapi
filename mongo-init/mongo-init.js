db = db.getSiblingDB('mydatabase');  // Select the database (create if it doesn't exist)

db.createCollection('users');  // Create the users collection

db.users.insertMany([
  {
    name: "John",
    email: "johh@hacktraining.nl",
    password: "password1",
    role: "user"
  }
]);

db.createCollection('energy');  // Create the energy collection

db.energy.insertMany([
  {
    timestamp: new Date("2024-01-01T00:00:00Z"),
    usage: 150.5,
    production: 75.3
  },
  {
    timestamp: new Date("2024-01-01T01:00:00Z"),
    usage: 145.2,
    production: 80.1
  },
  {
    timestamp: new Date("2024-01-01T02:00:00Z"),
    usage: 160.7,
    production: 70.6
  },
  {
    timestamp: new Date("2024-01-01T03:00:00Z"),
    usage: 155.4,
    production: 65.4
  },
  {
    timestamp: new Date("2024-01-01T04:00:00Z"),
    usage: 170.9,
    production: 60.2
  },
  {
    timestamp: new Date("2024-01-01T05:00:00Z"),
    usage: 165.6,
    production: 55.8
  },
  {
    timestamp: new Date("2024-01-01T06:00:00Z"),
    usage: 180.1,
    production: 50.4
  },
  {
    timestamp: new Date("2024-01-01T07:00:00Z"),
    usage: 175.8,
    production: 45.2
  },
  {
    timestamp: new Date("2024-01-01T08:00:00Z"),
    usage: 190.3,
    production: 40.1
  },
  {
    timestamp: new Date("2024-01-01T09:00:00Z"),
    usage: 185.0,
    production: 35.0
  },
  {
    timestamp: new Date("2024-01-01T10:00:00Z"),
    usage: 200.5,
    production: 30.0
  },
  {
    timestamp: new Date("2024-01-01T11:00:00Z"),
    usage: 195.2,
    production: 25.0
  },
  {
    timestamp: new Date("2024-01-01T12:00:00Z"),
    usage: 210.7,
    production: 20.0
  },
  {
    timestamp: new Date("2024-01-01T13:00:00Z"),
    usage: 205.4,
    production: 15.0
  },
  {
    timestamp: new Date("2024-01-01T14:00:00Z"),
    usage: 220.9,
    production: 10.0
  },
  {
    timestamp: new Date("2024-01-01T15:00:00Z"),
    usage: 215.6,
    production: 5.0
  },
  {
    timestamp: new Date("2024-01-01T16:00:00Z"),
    usage: 230.1,
    production: 0.0
  },
  {
    timestamp: new Date("2024-01-01T17:00:00Z"),
    usage: 225.8,
    production: 0.0
  },
  {
    timestamp: new Date("2024-01-01T18:00:00Z"),
    usage: 240.3,
    production: 0.0
  },
  {
    timestamp: new Date("2024-01-01T19:00:00Z"),
    usage: 235.0,
    production: 0.0
  },
  {
    timestamp: new Date("2024-01-01T20:00:00Z"),
    usage: 250.5,
    production: 0.0
  },
  {
    timestamp: new Date("2024-01-01T21:00:00Z"),
    usage: 245.2,
    production: 0.0
  },
  {
    timestamp: new Date("2024-01-01T22:00:00Z"),
    usage: 260.7,
    production: 0.0
  },
  {
    timestamp: new Date("2024-01-01T23:00:00Z"),
    usage: 255.4,
    production: 0.0
  }
]);
