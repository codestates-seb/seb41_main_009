import axios from 'axios';
import { Box, Label, SignupInput, CheckButton } from '../../atoms/signup/SignupComponents';
import { isValidEmail } from '../../../functions/isValid';
import { INVALID_EMAIL } from '../../../constants/Messages';

const EmailBox = ({ email, setEmail, emailMessage, setEmailMessage, setEmailValidation }) => {
  const onEmailInput = e => {
    const emailValue = e.target.value;

    setEmail(emailValue);

    if (isValidEmail(emailValue) || emailValue.length === 0) {
      setEmailMessage('');
    } else {
      setEmailMessage(INVALID_EMAIL);
    }
  };

  const verifyEmail = () => {
    const url = 'auth/certifications';

    if (emailMessage || email.length === 0) return;

    axios
      .post(url, email, {
        headers: {
          'content-type': 'text/plain',
        },
      })
      .then(res => {
        console.log(res);
        setEmailValidation(true);
      })
      .catch(err => console.log(err))
      .finally(() => {
        setEmailValidation(true);
      });
  };

  return (
    <Box>
      <Label>Email</Label>
      <SignupInput
        placeholder="Enter Your Email"
        onChange={onEmailInput}
        message={emailMessage}
        asideInput={<CheckButton onClick={verifyEmail}>이메일 인증</CheckButton>}
      />
    </Box>
  );
};

export default EmailBox;
