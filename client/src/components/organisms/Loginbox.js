import styled from 'styled-components';
import InputCard from '../molecules/InputCard';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 300px;
  margin-top: 15px;
  justify-content: center;
  align-items: center;
`;

const LoginInput = ({ width, height, type, placeholder, onChange, messageColor, message, asideInput }) => {
  return (
    <InputCard
      width={width}
      height={height}
      boxShadow="var(--boxShadow-00) black"
      type={'' || type}
      placeholder={placeholder}
      inputWidth="100%"
      inputHeight="20px"
      onChange={onChange}
      asideInput={asideInput}
      message={message}
      messageColor={messageColor}
    />
  );
};

const Loginbox = () => {
  return (
    <Container>
      <LoginInput placeholder="Enter Your Email" />
      <LoginInput type="password" placeholder="Enter Your Password" />
    </Container>
  );
};

export default Loginbox;
