// seeds/04_showtimes.js
exports.seed = async function(knex) {
  await knex('showtime').del();
  await knex('showtime').insert([
    { id: 1, start_time: new Date('2025-05-04T18:00:00'), movie_id: 1, room_id: 1, price: 75000 },
    { id: 2, start_time: new Date('2025-05-05T20:00:00'), movie_id: 1, room_id: 2, price: 90000 }
  ]);
};
