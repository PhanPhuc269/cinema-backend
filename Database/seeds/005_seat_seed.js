//exports.seed = async function(knex) {
//  await knex('seat').del();
//  await knex('seat').insert([
//    { id: 1, seat_number: 'A1', seat_row: 'A', seat_column: 1, is_present: true, type: 'NORMAL', room_id: 1 },
//    { id: 2, seat_number: 'A2', seat_row: 'A', seat_column: 2, is_present: true, type: 'VIP', room_id: 1 },
//    { id: 3, seat_number: 'B1', seat_row: 'B', seat_column: 1, is_present: true, type: 'SWEET_BOX', room_id: 2 }
//  ]);
//};

exports.seed = async function (knex) {
  // Xóa dữ liệu cũ trong bảng seats (nếu cần)
  await knex('seat').del();

  const rows = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"];
  const bookedSeats = ["A3", "A4", "B5", "C7", "D2", "D3", "E8", "F1", "G10", "H6", "J9", "K2"];
  const notPresentSeats = ["C5", "C6", "F5", "F6", "I5", "I6"]; // Giả lập vật cản ở giữa một số hàng

  const seats = [];
  let idCounter = 1;

  rows.forEach((row) => {
    const isVipRow = row === "J" || row === "K";
    const isCoupleRow = row === "L";
    const seatsPerRow = isCoupleRow ? 5 : 10;

    for (let i = 1; i <= seatsPerRow; i++) {
      const seatName = `${row}${i}`;
      seats.push({
        id: idCounter++,
        seat_number: seatName,
        seat_row: row,
        seat_column: i,
        type: isCoupleRow ? "COUPLE" : isVipRow ? "VIP" : "NORMAL",
//        status: bookedSeats.includes(seatName) ? "BOOKED" : "AVAILABLE",
        is_present: !notPresentSeats.includes(seatName), // present: false nếu ghế là vật cản
        room_id: 1, // Giả sử tất cả ghế đều thuộc về phòng 1
      });
    }
  });

  // Chèn dữ liệu vào bảng seats
  await knex('seat').insert(seats);
};