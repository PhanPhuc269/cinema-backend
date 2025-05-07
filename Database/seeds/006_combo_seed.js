// seeds/05_combos.js
exports.seed = async function(knex) {
  await knex('combo').del();
  await knex('combo').insert([
    { id: 1, name: 'Combo 1', description: 'Popcorn + Pepsi', price: 50000, available: true, image_url: 'combo1.png' },
    { id: 2, name: 'Combo 2', description: 'Nachos + Coke', price: 60000, available: true, image_url: 'combo2.png' }
  ]);
};
