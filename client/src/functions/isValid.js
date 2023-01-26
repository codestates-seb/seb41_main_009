import { EMAILREGEXP, NICKNAMEREGEXP, PASSWORDREGEXP } from '../constants/Regexp';

const isValidEmail = email => {
  return EMAILREGEXP.test(email);
};

const isValidPassword = password => {
  return PASSWORDREGEXP.test(password);
};

const isValidNickname = nickname => {
  return NICKNAMEREGEXP.test(nickname);
};

const isValidIntroduction = description => {
  return description.length <= 50;
};

export { isValidEmail, isValidPassword, isValidNickname, isValidIntroduction };
