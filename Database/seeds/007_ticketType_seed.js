// knex seed file: seeds/ticket_types_seed.js

/**
 * @param {import('knex')} knex
 */
exports.seed = async function (knex) {
  // Deletes ALL existing entries
  await knex('ticket_type').del();

  // Inserts seed entries
  await knex('ticket_type').insert([
    {
      name: 'Người lớn',
      description: 'Vé dành cho người lớn',
      price: 65000,
      type: 'adult',
    },
    {
      name: 'HSSV-Người cao tuổi',
      description: 'Vé dành cho học sinh, sinh viên và người cao tuổi',
      price: 45000,
      type: 'student',
    },
    {
      name: 'Trẻ em',
      description: 'Vé dành cho trẻ em dưới 12 tuổi',
      price: 40000,
      type: 'child',
    },
    {
      name: 'Người lớn đôi',
      description: 'Vé đôi dành cho người lớn',
      price: 135000,
      type: 'couple',
    },
  ]);
};