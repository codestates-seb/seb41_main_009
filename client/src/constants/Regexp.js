/* eslint-disable */

const EMAILREGEXP = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
const PASSWORDREGEXP = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
const NICKNAMEREGEXP = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,15}$/;

export { EMAILREGEXP, PASSWORDREGEXP, NICKNAMEREGEXP };
