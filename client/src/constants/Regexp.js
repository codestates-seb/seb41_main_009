/* eslint-disable */

const EMAILREGEXP = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
const PASSWORDREGEXP = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

export { EMAILREGEXP, PASSWORDREGEXP };
