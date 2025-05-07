exports.seed = async function(knex) {
  await knex('seat').del();
  await knex('seat').insert([
    { id: 1, seat_number: 'A1', seat_row: 'A', seat_column: 1, is_present: true, type: 'NORMAL', room_id: 1 },
    { id: 2, seat_number: 'A2', seat_row: 'A', seat_column: 2, is_present: true, type: 'VIP', room_id: 1 },
    { id: 3, seat_number: 'B1', seat_row: 'B', seat_column: 1, is_present: true, type: 'SWEET_BOX', room_id: 2 }
  ]);
};