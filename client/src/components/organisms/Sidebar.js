import styled from 'styled-components';
import Menu from '../molecules/SidebarMenu';
import { TextButton } from '../atoms/Buttons';

const Container = styled.div`
  width: var(--sidebar-width);
  height: auto;
  background-color: salmon;
`;

const MenuList = styled.div`
  display: flex;
  position: fixed;
  z-index: 1;
  justify-content: flex-start;
  flex-direction: column;
`;

const Sidebar = () => {
  return (
    <Container>
      <MenuList>
        <TextButton href="/">Home</TextButton>
        <TextButton href="/">Showcase</TextButton>
        <TextButton href="/">All</TextButton>

        <Menu />
        <Menu />
        <Menu />
        <Menu />
      </MenuList>
    </Container>
  );
};

export default Sidebar;
