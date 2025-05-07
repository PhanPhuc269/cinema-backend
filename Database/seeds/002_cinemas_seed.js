/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function(knex) {
  await knex('cinema').del();
  await knex('cinema').insert([
    {
      name: 'Cinestar Quốc Thanh',
      address: '271 Nguyễn Trãi, Quận 1, TP.HCM',
      city: 'TP.HCM',
      phone: '028 3920 9123',
      image_url: 'https://cinestar.com.vn/pictures/Cinestar/rap-quoc-thanh.jpg'
    },
    {
      name: 'Cinestar Huế',
      address: '25 Hai Bà Trưng, TP. Huế',
      city: 'Huế',
      phone: '0234 3833 777',
      image_url: 'https://cinestar.com.vn/pictures/Cinestar/rap-hue.jpg'
    },
    {
      name: 'Cinestar Đà Lạt',
      address: 'Đường Phan Bội Châu, TP. Đà Lạt',
      city: 'Đà Lạt',
      phone: '0263 3511 777',
      image_url: 'https://cinestar.com.vn/pictures/Cinestar/rap-da-lat.jpg'
    },
    {
      name: 'Cinestar Hai Bà Trưng',
      address: '135 Hai Bà Trưng, Quận 1, TP.HCM',
      city: 'TP.HCM',
      phone: '028 3925 7755',
      image_url: 'https://cinestar.com.vn/pictures/Cinestar/rap-hai-ba-trung.jpg'
    },
    {
      name: 'Cinestar Bình Dương',
      address: 'Tầng 5, TTTM Becamex Tower, TP. Thủ Dầu Một',
      city: 'Bình Dương',
      phone: '0274 3810 777',
      image_url: 'https://cinestar.com.vn/pictures/Cinestar/rap-binh-duong.jpg'
    }
  ]);
};
