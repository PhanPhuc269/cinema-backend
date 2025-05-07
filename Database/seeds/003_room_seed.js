// seeds/02_rooms.js
exports.seed = async function(knex) {
  await knex('room').del();
  await knex('room').insert([
    { id: 1, name: 'Room 1', total_seats: 50, cinema_id: 1 },
    { id: 2, name: 'Room 2', total_seats: 70, cinema_id: 1 }
  ]);
};
